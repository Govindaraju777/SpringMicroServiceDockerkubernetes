1)


PS C:\Program Files\Filebeat> pwd
Path
----
C:\Program Files\Filebeat

S C:\Program Files\Filebeat> ls

    Directory: C:\Program Files\Filebeat
Mode                LastWriteTime         Length Name
 
d-----       19-10-2019     14:18                filebeat
d-----       01-11-2019     23:42                filebeat-7.4.2-windows-x86_64
d-----       19-10-2019     14:18                kibana
 
-a----       29-10-2019     01:18            802 README.md
-a----       29-10-2019     01:18            250 uninstall-service-filebeat.ps1




PS C:\Program Files\Filebeat> .\filebeat.exe modules disable logstash
Module logstash is already disabled
PS C:\Program Files\Filebeat> Start-Service filebeat



PS C:\Program Files\Filebeat> .\install-service-filebeat.ps1^C
PS C:\Program Files\Filebeat> .\filebeat.exe modules disable logstash^C
PS C:\Program Files\Filebeat> Start-Service filebeat


