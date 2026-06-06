<template>
  <div class="page-container">
    <a-result status="404" title="404" sub-title="抱歉，页面不存在">
      <template #extra>
        <a-button type="primary" @click="$router.push('/')">返回首页</a-button>
      </template>
    </a-result>
  </div>
</template>

<style scoped>
.page-container { display: flex; justify-content: center; align-items: center; height: 100%; }
</style>
