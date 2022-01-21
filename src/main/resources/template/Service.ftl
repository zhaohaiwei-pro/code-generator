package ${config.servicePackage};

import java.util.List;

import com.mozi.corex.common.utils.page.Page;
import ${config.entityPackage}.${entityName!''};
<#assign injection = entityName?uncap_first>
/**
 * ${entityComment!''}Service接口
 * @author ${base.author!''}
 * @date ${systemTime!''}
 * @version ${base.version!''}
 */
public interface ${serviceName} {

    /**
     * ${entityComment!''} 分页查询
     * @param page
     * @return Page<${entityName}>
     */
    Page<${entityName}> searchByPage(Page<${entityName}> page);

    /**
     * ${entityComment!''} 查询全部
     * @param page
     * @return List<${entityName}>
     */
    List<${entityName}> getAll(Page<${entityName}> page);

    /**
     * ${entityComment!''} 保存
     * @param ${injection}
     * @return 主键id
     */
    String save${entityName}(${entityName} ${injection});

    /**
     * ${entityComment!''} 批量保存
     * @param ${injection}List
     */
    void batchSave${entityName}(List<${entityName}> ${injection}List);

    /**
     * ${entityComment!''} 更新
     * @param ${injection}
     */
    void update${entityName}(${entityName} ${injection});

    /**
     * ${entityComment!''} 删除
     * @param id
     */
    void delete${entityName}(String id);

    /**
     * ${entityComment!''} 根据主键查询
     * @param id
     * @return ${entityName}
     */
    ${entityName} get(String id);

}