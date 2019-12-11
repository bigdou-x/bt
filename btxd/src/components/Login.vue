<template>
  <el-form :model="user" :rules="rules" v-loading="loading" class="login-container" ref="loginForm">
    <el-form-item prop="email">
      <el-input v-model="user.email" placeholder="请输入邮箱" clearable></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input placeholder="请输入密码" v-model="user.password" show-password></el-input>
    </el-form-item>
    <el-form-item prop="remember-me">
      <el-checkbox v-model="rememberme" label="记住我" style="float:left"></el-checkbox>
    </el-form-item>
    <el-form-item label>
      <el-button type="primary" plain @click="login()">登录</el-button>
      <el-button type="primary" plain @click="registry()">注册</el-button>
    </el-form-item>
  </el-form>
</template>


<script>
export default {
  data() {
    return {
      user: {
        email: "",
        password: ""
      },
      rememberme: false,
      loading: false,
      rules: {
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        password: [
          { required: true, message: "请输入密码" },
          { min: 5, max: 50, message: "长度最小为3，最大为50" }
        ]
      }
    };
  },
  methods: {
    registry() {
      this.$refs["loginForm"].validate(valid => {
        if (valid) {
          this.$axios({
            method: "post",
            url: "/registry",
            data: this.user
          }).then(resp => {});
        } else {
          console.info("fail");
          return;
        }
      });
    },
    login() {
      let _this = this;
      this.loading = true;
      this.$refs["loginForm"].validate(valid => {
        if (valid) {
          _this
            .postRequest("/login", {
              email: this.user.email,
              password: this.user.password,
              rememberMe: this.rememberme
            })
            .then(resp => {
              _this.loading = false;
              if (resp && resp.status == "200") {
                window.localStorage.setItem("email", this.user.email);
                var path = _this.$route.query.redirect;
                _this.$router.replace({
                  path: path == "/" || path == undefined ? "/home" : path
                });
              }
            });
        }
      });
    }
  }
};
</script>

<style>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
</style>