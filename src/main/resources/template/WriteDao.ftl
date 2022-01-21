package ${config.writeDaoPackage};

import java.util.List;

import com.mozi.corex.common.base.dao.OptSqlDao;
import ${config.entityPackage}.${entityName};

/**
 * ${entityComment!''}OptDao
 * @author ${base.author!''}
 * @date ${systemTime!''}
 * @version ${base.version!''}
 */
public interface ${writeDaoName} extends OptSqlDao<${entityName}, String> {

    /**
     * 批量保存
     * @param list
     */
    void batchSave${entityName}(List<${entityName}> list);
}
