@echo off
set datetimef=%date:~6,4%%date:~3,2%%date:~0,2%

pushd D:\CA\RC

set JAVA_HOME="C:\Program Files\Java\jre1.8.0_40"

setLocal EnableDelayedExpansion
 set CLASSPATH="
 for /R lib %%a in (*.jar) do (
   set CLASSPATH=!CLASSPATH!;%%a
 )
 set CLASSPATH=!CLASSPATH!"
 set CLASSPATH=!CLASSPATH!;config

!JAVA_HOME!\bin\java -Xmx256m -classpath !CLASSPATH! relationshipChecker.Main
