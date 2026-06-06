<template>
  <div class="page-container">
    <a-result status="403" title="401" sub-title="抱歉，您没有权限访问此页面">
      <template #extra>
        <a-button type="primary" @click="$router.push('/')">返回首页</a-button>
      </template>
    </a-result>
  </div>
</template>

<style scoped>
.page-container { display: flex; justify-content: center; align-items: center; height: 100%; }
</style>
