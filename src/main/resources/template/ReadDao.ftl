package ${config.readDaoPackage};

import com.mozi.corex.common.base.dao.QrySqlDao;
import ${config.entityPackage}.${entityName};

/**
 * ${entityComment!''}QryDao
 * @author ${base.author!''}
 * @date ${systemTime!''}
 * @version ${base.version!''}
 */
public interface ${readDaoName} extends QrySqlDao<${entityName}, String> {

}
