<template>
  <div>
    <el-container class="home-container">
      <el-header class="home-header">
        <span style="float:left">后台管理系统</span>
        <div style="position:absolute;right:0px;width:100px;">
          <el-dropdown @command="handleCommand">
            <span class="user-info">{{user}}</span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item command="logout">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside width="180px" style="background: #ececec">
          <div style="display: flex;justify-content: flex-start;width: 180px;text-align: left;"></div>
          <el-menu default-active="1" style="background: #ececec;width: 180px;" router>
            <template v-for="(item, index) in this.routes">
              <el-submenu :index="index+''" :key="index">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span>{{item.name}}</span>
                </template>
                <el-menu-item
                  v-for="child in item.children"
                  :index="child.path"
                  :key="child.path"
                >{{child.name}}</el-menu-item>
              </el-submenu>
            </template>
          </el-menu>
        </el-aside>

        <el-container>
          <el-main>
            <router-view></router-view>
          </el-main>
          <el-footer>@2019 xd</el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  computed: {
    user() {
      return window.localStorage.getItem("email");
    },
    routes() {
      return this.$store.state.routes;
    }
  },
  methods: {
    handleCommand(cmd) {
      var _this = this;
      if (cmd == "logout") {
        this.getRequest("/logout")
          .then(resp => {
            window.localStorage.setItem("email", null);
            _this.$router.replace({ path: "/" });
          })
          .catch(err => {});
      }
    }
  }
};
</script>
<style>
.home-container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}
.home-header {
  background-color: #20a0ff;
  color: #333;
  text-align: center;
  display: flex;
  align-items: center;
}
.user-info {
  cursor: pointer;
}
</style>