
#!/bin/sh
echo "-------------------------------------------------------------------------------------------"
echo "Starting at `date`"
cd /opt/app/t1eam4c2/sltest/branches/b1505/1505B_p1_Grid_On/1505B_myATT_Recording_GridOn/slsupport
recorderDownList="`pwd`/recorder_down_list"

#Flag=`cat execution | cut -d"=" -f2`
#echo "[$Flag]"
Flag=OFF
if [[ $Flag == 'ON' ]]; then

	echo -e "Please check plth238 1505B myATT ,Following recorders are down" > recorder_down_list
	for _beDetails in `cat beList`;
        do
            var=$(echo $_beDetails | awk -F"#" '{print $1,$2,$3,$4,$5,$6,$7}')
                set -- $var
                _beName=$1;
		_bePID=`ps -ef | egrep -wi ${_beName}'_.*\.vsm' | grep -v grep | awk '{print $2}'`
		if [ "${_bePID}" == "" ]
	        then
			echo "Recorder for $_beName not running"
			echo -e "$_beName\\n"  >> $recorderDownList
		else
			echo "Recorder for $_beName is running with PID = $_bePID"
		fi
        done


	return_code=`wc -l recorder_down_list | cut -d" " -f1`
	if [[ $return_code -gt 1 ]]; then 
	echo "going good"

	mailx -s "Recorders are down please check plth238 1505B myATT" IBUTUS08VirtualServicesiTKO@Techmahindra.com  <recorder_down_list
	else 
		echo "All Backends are UP" >> recorder.log
	fi
else
	echo "Flag is OFF please turn it ON"
fi
echo "Ending at `date`"
echo "-------------------------------------------------------------------------------------------"


#	for  _beName in `cat FakebeList`;
#	do
#		_bePID=`ps -ef | egrep -wi ${_beName}'_.*\.vsm' | grep -v grep | awk '{print $2}'`
#               if [ "${_bePID}" == "" ]
#               then
#
#                echo "Recorder for $_beName not running"
#		echo -e "$_beName\\n" >> $recorderDownList
#                else
#                        echo "Recorder for $_beName is running with PID = $_bePID"
#               fi

#done

#return_code=`wc -l recorder_down_list | cut -d" " -f1`
#if [[ $return_code -gt 1 ]]; then 
#mailx -s "Recorders are down please check" pg00337943@techmahindra.com <recorder_down_list
#fi
