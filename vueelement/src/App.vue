<template>
  <div id="app">
    <el-row>
      <el-col :span="4">
        <menuComponent></menuComponent>
      </el-col>

      <el-col :span="20">
        <router-view></router-view>
      </el-col>

    </el-row>
  </div>
</template>

<script>

  import menu from '@/components/menu'

  export default {
    name: 'App',
    components: {
      'menuComponent': menu
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
