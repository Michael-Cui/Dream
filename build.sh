SRC_BASE="/d/cph/workspace/Dream"
RUNTIME_BASE="/d/cph/workspace/product"

# compile
cd $SRC_BASE
mvn clean install -DskipTests

# copy files
mkdir -p ${RUNTIME_BASE}/services/config

cp ${SRC_BASE}/config/target/config.jar ${RUNTIME_BASE}/services/config/

mkdir -p ${RUNTIME_BASE}/services/registry

cp ${SRC_BASE}/registry/target/registry.jar ${RUNTIME_BASE}/services/registry/

mkdir -p ${RUNTIME_BASE}/services/gateway

cp ${SRC_BASE}/gateway/target/gateway.jar ${RUNTIME_BASE}/services/gateway/

mkdir -p ${RUNTIME_BASE}/services/chain

cp ${SRC_BASE}/chain/target/chain.jar ${RUNTIME_BASE}/services/chain/

