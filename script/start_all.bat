SET SCRIPT_BASE=D:\workspace\Dream\script
start cmd /k %SCRIPT_BASE%\start_config.bat
choice /t 5 /d y

start cmd /k %SCRIPT_BASE%\start_registry.bat
choice /t 5 /d y

start cmd /k %SCRIPT_BASE%\start_gateway.bat
choice /t 5 /d y
