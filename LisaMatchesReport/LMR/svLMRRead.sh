#!/bin/sh
unset DISPLAY

LOG_FILE="/opt/app/DevTest/LisaMatchesReport/LMR/logs"
echo "Inside shell script"
echo "$1"
DATE=`date +%Y%m%d`
echo "DATE=`date +%Y%m%d`"

echo "New Date is ${DATE}"

mkdir -p "/opt/app/DevTest/LisaMatchesReport/LMR/logs/${DATE}"

ant -buildfile /opt/app/DevTest/LisaMatchesReport/LMR/build.xml -DArg1="$1" LMRRead | tee ${LOG_FILE}/${DATE}/$1.log


chmod 777 -R "/opt/app/DevTest/LisaMatchesReport/LMR/LMR_VSIs/Extracted/"

