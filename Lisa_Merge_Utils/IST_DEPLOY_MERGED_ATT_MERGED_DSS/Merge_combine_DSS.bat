@ECHO OFF
ECHO Script to Merge DSS Merged  and ATT Merged
set SOURCE_FOLDER=C:/stubbing/branches/b1503/1503_p1_Grid_On/1503_DSS_Validation_GridOn_merge/1503_DSS_Validation_GridOn_merge/VServices/MERGED/
set DESTINATION_FOLDER=C:/stubbing/trunk/IST_Deploy/MYATT_DSS_COMBINE_2014_FINAL/MYATT_DSS_COMBINE_2014_FINAL/VServices/MERGED/



ECHO ********** Starting Combine process ************
echo Source folder = %SOURCE_FOLDER%
echo Destination folder = %DESTINATION_FOLDER%

for %%x in (
    JAVA/Images/BDM_Merged.vsi
    JAVA/Images/CAAM_Merged.vsi
    JAVA/Images/CAPM_Merged.vsi
    JAVA/Images/ENABLER_Merged.vsi
    JAVA/Images/GRID_Merged.vsi
    JAVA/Images/LDAP_Merged.vsi
    JAVA/Images/LIM_Merged.vsi
    JAVA/Images/PAMBIS_Merged.vsi
    JAVA/Images/QP_Merged.vsi
    JAVA/Images/SMBIS_Merged.vsi
    JAVA/Images/SMS_Merged.vsi
    JAVA/Images/DLC_Merged.vsi
    JAVA/Images/EDP_Merged.vsi
    JAVA/Images/TLG_Merged.vsi  
    WEBSERVICES/Images/AMNQ_Merged.vsi
    WEBSERVICES/Images/BCAM_Merged.vsi
    WEBSERVICES/Images/BDS_Merged.vsi
    WEBSERVICES/Images/BDS-CAAM_Merged.vsi
    WEBSERVICES/Images/BIM_Merged.vsi
    WEBSERVICES/Images/BOOST_Merged.vsi
    WEBSERVICES/Images/CSI_Merged.vsi
    WEBSERVICES/Images/CSI-IFSQ_Merged.vsi
    WEBSERVICES/Images/EFC_Merged.vsi
    WEBSERVICES/Images/ICBT_Merged.vsi
    WEBSERVICES/Images/K2VMS_Merged.vsi
    WEBSERVICES/Images/MPS_Merged.vsi
    WEBSERVICES/Images/MPS-IGP_Merged.vsi
    WEBSERVICES/Images/MRE_Merged.vsi
    WEBSERVICES/Images/REDKNEE_Merged.vsi
    WEBSERVICES/Images/ROAR_Merged.vsi
    WEBSERVICES/Images/SDP_Merged.vsi
    WEBSERVICES/Images/SPD_Merged.vsi
    WEBSERVICES/Images/TGUARD_Merged.vsi
    WEBSERVICES/Images/VRI_Merged.vsi
    WEBSERVICES/Images/XI_Merged.vsi
    WEBSERVICES/Images/YODA_Merged.vsi
    WEBSERVICES/Images/TGUARD-GEN_Merged.vsi
    WEBSERVICES/Images/TGUARD-VAL_Merged.vsi
    
) do (
    echo Combining %%x
    C:/Lisa7.5/bin/ServiceImageManager.exe --combine="%DESTINATION_FOLDER%%%x" --favor=target "%SOURCE_FOLDER%%%x"
    echo Done
)

