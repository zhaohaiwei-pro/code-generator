<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${config.readDaoPackage}.${readDaoName}">

    <resultMap id="${entityName}ExtendResultMap" type="${entityName}Vo" extends="${entityName}ResultMap">

    </resultMap>

    <!--非自动生成的 业务sql 写到这里 -->

</mapper>