package com.mindskip.xzs.infrastructure;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://localhost:5432/xzs", "postgres", "123456")
                .globalConfig(builder -> builder
                        .author("xzs")
                        .outputDir("xzs-infrastructure/src/main/java")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("com.mindskip.xzs.infrastructure.persistence")
                        .entity("mybatis.entity")
                        .mapper("mybatis.mapper")
                        .xml("mybatis.mapper")
                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok()
                        .disableSerialVersionUID()
                        .controllerBuilder()
                        .disable()
                        .serviceBuilder()
                        .disable()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
