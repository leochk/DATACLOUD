#!/bin/bash
#title           :datagenerator.sh
#description     :This script will generate randomly data
#author		 :Jonathan Lejeune
#date            :20181031
#==============================================================================
pwd_avant=$PWD
cd `dirname $0`
WORKING_DIRECTORY=$PWD
cd $pwd_avant


error(){ 
    echo "$0 ERREUR : parametres invalides !" >&2 
    echo "utilisez l'option -h pour en savoir plus" >&2 
    exit 1 
} 

usage(){ 
    echo "Usage: $0 [options]" 
    
    echo "options obligatoires :"
    echo "	--type ou -t : type de données à générer : [hbaseschema, twit, textfile, filemultiplicator]"
    echo "	--fileconf ou -f : fichier de configuration du générateur"
    echo "options facultatives :"
    echo "	--help ou -h : afficher l'aide"
    echo "	--compile ou -c : (re)compiler les jar du générateur"
} 

generatejar() {
	for jar in $1
	do
		echo "generating $jar"
		sources="$DIRECTORY_SRC/src_`basename $jar| cut -d '.' -f1`"
		scalac $option_classpath -d $jar  $(find $sources -name \*.scala)
	done
}



# -o : options courtes 
# -l : options longues 
options=$(getopt -o h,c,t:,f: -l help,compile,type:,fileconf: -- "$@") 

# éclatement de $options en $1, $2... 
#set -- If no arguments follow this option, then the positional parameters are unset.  Otherwise, the positional parameters are set to the arguments,  even if some of them begin with a `-'. 
set -- $options 

compile="false"
type=""
DIRECTORY_SRC="$WORKING_DIRECTORY/scala_src"

classpath=""


while true; do 
    case "$1" in 
    	-h|--help) usage 
            shift;; # on décale la liste des options de 1 
            
        -c|--compile)
            compile="true"
            shift ;; # on décale la liste des options de 2 ($1 et $2 sont remplacés par $3 et $4 s'ils existent) 
        
        -t|--type)
            type=$(echo $2 | sed -e "s/'//g")
            required_jar="$WORKING_DIRECTORY/datagenerator.jar"
            target_jar="$WORKING_DIRECTORY/datagenerator.jar"
            classpath=""
            if [ "$type" = "hbaseschema" ]
            then
            	[ "`env | grep MY_HBASE_HOME`" = "" ] &&  echo "la variable d'environnement $MY_HBASE_HOME doit être définie" && exit 1
            	classpath="$WORKING_DIRECTORY/datagenerator.jar:"$(ls -l $MY_HBASE_HOME/lib/*.jar | tr -s ' ' | cut -d ' ' -f 9 | tr '\n' ':')
            	required_jar="$required_jar $WORKING_DIRECTORY/datagenerator_hbaseext.jar"
            	target_jar="$WORKING_DIRECTORY/datagenerator_hbaseext.jar"
            fi
            for jar in $required_jar
            do
            	if ! [ -e $jar ] ; then compile="true" ; break ; fi
            done
            shift 2
            ;;
        -f|--fileconf)
            fichier=$(echo $2 | sed -e "s/'//g")
            shift 2
            ;;
        --) # fin des options 
            shift # on décale la liste des options de 1 
            break;; 
        *) error 
            shift;; # on décale la liste des options de 1 
    esac 
done

[ gen"$type" = "" ] || [ "$fichier" = "" ] && error

option_classpath="-classpath $classpath"
if [ "$classpath" = "" ] ; then option_classpath="" ; fi

[ "$compile" = "true" ] && generatejar "$required_jar"

scala $option_classpath $target_jar $fichier


