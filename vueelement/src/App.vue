<template>
  <div id="app">
    <!--<el-row>
      <el-col :span="4" :show="isShowMenu">
        <menuComponent></menuComponent>
      </el-col>

      <el-col :span="20">
        <router-view></router-view>
      </el-col>

    </el-row>-->
    <router-view></router-view>
  </div>
</template>

<script>

//  import menu from '@/components/menu'

  export default {
    name: 'App',
//    components: {
//      'menuComponent': menu
//    },
  }
</script>

<style>
  body{
    padding:0px;
    margin:0px auto;
  }
  a{
    text-decoration:none;
  }
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: left;
    color: #2c3e50;
  }
</style>
