set DIR=C:\Users\jdk\Desktop\power-mall
copy %DIR%\mall-cms\cms-service\target\app.jar %DIR%\docker\cms-service\app.jar
copy %DIR%\mall-oms\oms-service\target\app.jar %DIR%\docker\oms-service\app.jar
copy %DIR%\mall-pms\pms-service\target\app.jar %DIR%\docker\pms-service\app.jar
copy %DIR%\mall-search\search-service\target\app.jar %DIR%\docker\search-service\app.jar
copy %DIR%\mall-ums\ums-service\target\app.jar %DIR%\docker\ums-service\app.jar
copy %DIR%\power-gateway\target\app.jar %DIR%\docker\power-gateway\app.jar
copy %DIR%\power-oss\oss-service\target\app.jar %DIR%\docker\power-oss\app.jar




