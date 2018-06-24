<template>
  <el-row class="tac">
    <el-col>
      <h5>自定义颜色</h5>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        :router="true">
        <template v-for="menu in menuData">
          <el-menu-item v-if="menu.children.length == 0" :index="menu.id" :route="menu.href">{{menu.text}}</el-menu-item>

          <el-submenu v-else :index="menu.id">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>{{menu.text}}</span>
            </template>

            <el-menu-item  v-for="subMenu in menu.children" :index="subMenu.id" :route="subMenu.href">{{subMenu.text}}</el-menu-item>
          </el-submenu>
        </template>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
  import menuApi from '@/api/menu';

  export default {
    data(){
      return {
        menuData: []
      }
    },
    created() {
      this.initMenu();
    },
    methods: {
      initMenu() {
        menuApi.getMenu().then(data=>this.menuData = data);
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
