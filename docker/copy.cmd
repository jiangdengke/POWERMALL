set DIR=/home/jdk/workspace/power-mall/POWERMALL
@REM set DIR=C:\Users\jiangdk\Desktop\workspace\code\power-mall\POWERMALL
copy %DIR%\mall-cms\cms-service\target\app.jar %DIR%\docker\cms-service\app.jar
copy %DIR%\mall-oms\oms-service\target\app.jar %DIR%\docker\oms-service\app.jar
copy %DIR%\mall-pms\pms-service\target\app.jar %DIR%\docker\pms-service\app.jar
copy %DIR%\mall-search\search-service\target\app.jar %DIR%\docker\search-service\app.jar
copy %DIR%\mall-ums\ums-service\target\app.jar %DIR%\docker\ums-service\app.jar
copy %DIR%\power-gateway\target\app.jar %DIR%\docker\power-gateway\app.jar
copy %DIR%\power-oss\oss-service\target\app.jar %DIR%\docker\oss-service\app.jar




