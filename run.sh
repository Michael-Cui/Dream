SRV_BASE="/d/workspace/product"
MYSQL_BASE="/d/Program/mysql-5.7.9-winx64"

#start mysql
# install mysql service :  ./mysqld --install
echo "Starting mysql..."
#${MYSQL_BASE}/bin/mysqld --standalone --console
# net start mysqld

#start services
echo "Starting config service..."
cd $SRV_BASE/services/config
nohup java -jar $SRV_BASE/services/config/config.jar > config.log 2>&1 &

echo "Starting registry service..."
cd $SRV_BASE/services/registry
nohup java -jar $SRV_BASE/services/registry/registry.jar > registry.log 2>&1 &

echo "Starting gateway service..."
cd $SRV_BASE/services/gateway
nohup java -jar $SRV_BASE/services/gateway/gateway.jar > gateway.log 2>&1 &

echo "Starting chain service..."
cd $SRV_BASE/services/chain
nohup java -jar $SRV_BASE/services/chain/chain.jar > chain.log 2>&1 &

