# xzs-next — 学之思考试系统 (COLA DDD 重构版)

[![License](https://img.shields.io/badge/license-Apache%202.0-blue)](LICENSE)

**xzs-next** 是 [学之思开源考试系统](https://github.com/mindskip/xzs) 的深度重构版本。原版 xzs 是一款优秀的 Java + Vue 前后端分离考试系统，但采用传统三层架构，随着功能增长出现了模块耦合、领域逻辑散落等问题。

xzs-next 在保留全部业务功能的前提下，以 **COLA 4.0 整洁架构** 和 **领域驱动设计（DDD）** 为指导思想进行了系统性重构，旨在提升可维护性、可测试性和代码可读性。

---

## 重构目标

| 维度 | 原版 (xzs) | 重构版 (xzs-next) |
|------|-----------|-------------------|
| 架构风格 | 传统三层 (Controller/Service/Mapper) | COLA 4.0 整洁架构 (Adapter/Application/Domain/Infrastructure) |
| Java 版本 | Java 8 | Java 17 |
| Spring Boot | 2.x | 3.2 |
| 依赖注入 | @Autowired 字段注入 | 构造器注入 |
| ORM | MyBatis + XML | MyBatis-Plus 3.5 |
| 事务边界 | 分散在 Service 层 | 统一在 Application Service |
| 领域逻辑 | 散落在 Service 和 Controller 中 | 收拢到 Domain Service + 聚合根 |
| 仓储层 | Mapper 直接暴露给 Service | Gateway 接口隔离 |
| 事件驱动 | 无 | ApplicationEvent 驱动异步处理 |
| 前端 | Vue 2 + Element UI | Vue 3 + Vite + TypeScript + Ant Design Vue 4 |
| 构建工具 | Maven (单体) | Maven (多模块) |
| 数据源 | Druid | HikariCP |

## 技术栈

**后端** — Java 17, Spring Boot 3.2, MyBatis-Plus 3.5, PostgreSQL, MapStruct, JWT

**前端** — Vue 3, Vite, TypeScript, Ant Design Vue 4, Pinia, ECharts, Axios

**部署** — Docker, Docker Compose, Nginx

## 模块结构

```
xzs-next/
├── xzs-adapter/        # 适配器层 — Controller, DTO, Assembler
├── xzs-application/    # 应用层 — 应用服务, 事件监听器
├── xzs-domain/         # 领域层 — 聚合根, 领域服务, 仓储接口
├── xzs-infrastructure/ # 基础设施层 — MyBatis-Plus 实现, Mapper, PO
├── xzs-common/         # 通用层 — 工具类, 枚举
├── xzs-start/          # 启动模块 — Spring Boot 入口, 配置
├── xzs-web/            # 前端
│   ├── admin/          #   管理后台 (Vue 3)
│   └── student/        #   学生端 (Vue 3)
└── sql/                # 数据库初始化脚本
```

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.8+
- PostgreSQL 14+
- Node.js 18+
- pnpm (推荐) 或 npm

### 后端

```bash
# 1. 创建数据库并执行初始化脚本
psql -U postgres -d xzs -f sql/init.sql

# 2. 修改配置 (数据库连接等)
#    vim xzs-start/src/main/resources/application-dev.yml

# 3. 编译并启动
mvn clean package -DskipTests
java -jar xzs-start/target/xzs-start-4.0.0.jar
```

### 前端

```bash
# 管理后台
cd xzs-web/admin
pnpm install
pnpm run dev    # 开发模式, 默认端口 8001

# 学生端
cd xzs-web/student
pnpm install
pnpm run dev    # 开发模式, 默认端口 8002
```

### Docker 部署

```bash
docker compose up -d
```

## API 文档

后端启动后访问: `http://localhost:8000/doc.html`

## 功能模块

### 学生端
- 登录 / 注册
- 任务中心（管理员发布的年级任务，每人仅限一次）
- 固定试卷（可重复练习、自行批改）
- 时段试卷（限时内可重复练习）
- 考试记录与成绩查看
- 错题本
- 个人信息管理
- 消息中心

### 管理后台
- 仪表盘（数据统计）
- 用户管理（学生 / 管理员）
- 学科管理
- 试卷管理（固定 / 时段 / 任务）
- 题目管理（单选 / 多选 / 判断 / 填空 / 简答）
- 任务管理
- 消息推送
- 操作日志

### 微信小程序
- 登录 / 注册（自动绑定微信账号）
- 任务中心
- 考试
- 考试记录
- 错题本
- 个人信息

## License

[Apache License 2.0](LICENSE)
