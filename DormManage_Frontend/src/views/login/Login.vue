<template>
  <body class='align'>
  <div class='login'>
    <header class='login__header'>
      <h2>
        <svg class='icon'>
          <use xlink:href='#icon-lock' />
        </svg>
        Sign In
      </h2>
    </header>
    <div class='login__form'>
      <div>
        <label for='email'>用户名</label>
        <input id='email' v-model='loginData.username' type='email' placeholder='username'>
      </div>
      <div>
        <label for='password'>密码</label>
        <input id='password' v-model='loginData.password' type='password' placeholder='password'>
      </div>
      <div>
        <a-radio-group v-model:value='loginData.role' button-style='solid'>
          <a-radio-button v-for='role in Object.keys(roleMap)' :key='role' :value='parseInt(role)'>{{ roleMap[role] }}
          </a-radio-button>
        </a-radio-group>
      </div>
      <div>
        <input class='button' value='登录' type='button' @click='login'>
      </div>
    </div>
  </div>
  <svg xmlns='http://www.w3.org/2000/svg' class='icons'>
    <symbol id='icon-lock' viewBox='0 0 448 512'>
      <path
        d='M400 224h-24v-72C376 68.2 307.8 0 224 0S72 68.2 72 152v72H48c-26.5 0-48 21.5-48 48v192c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V272c0-26.5-21.5-48-48-48zm-104 0H152v-72c0-39.7 32.3-72 72-72s72 32.3 72 72v72z' />
    </symbol>
  </svg>

  </body>
</template>

<script lang='ts' setup>
import { reactive, ref } from 'vue'
import { LoginAPI, LoginParams, roleMap } from '@/api/login'
import { message } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import useUserStore from '@/store/userStore'
import { loadRoutes } from '@/router'

const store = useUserStore()
const router = useRouter()
const loginData = reactive<LoginParams>({
  username: '',
  password: '',
  role: 0
})
const login = async () => {
  message.loading({ content: '登录中', key: 'login' })
  const { data: res } = await LoginAPI.login({
    ...loginData
  })
  if (res.code !== 200) {
    return message.error({
      content: res.message,
      key: 'login'
    })
  }
  store.$state.auth = true
  store.$state.currentRole = res.data.role
  store.$state.userInfo = res.data.useInfo
  loadRoutes()
  message.success({ content: '登录成功', key: 'login' })
  await router.push('/')
}
</script>

<style lang='scss' scoped>
/* ---------- GENERAL ---------- */
* {
  -webkit-box-sizing: inherit;
  box-sizing: inherit;
}

html {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  background-color: #e1e1e1;
  font-family: 'Varela Round', sans-serif;
  line-height: 1.5;
  margin: 0;
  min-height: 100vh;
  padding: 5vmin;
}

h2 {
  font-size: 1.75rem;
}

input {
  background-image: none;
  border: none;
  font: inherit;
  margin: 0;
  padding: 0;
  -webkit-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
}

svg {
  height: auto;
  max-width: 100%;
  vertical-align: middle;
}

/* ---------- ALIGN ---------- */
.align {
  display: grid;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  justify-items: center;
  place-items: center;
}

/* ---------- BUTTON ---------- */

.button {
  background-color: #33cc77;
  color: #fff;
  padding: 0.25em 1.5em;
}

.button:focus,
.button:hover {
  background-color: #28ad63;
}

/* ---------- ICONS ---------- */
.icons {
  display: none;
}

.icon {
  fill: currentcolor;
  display: inline-block;
  height: 1em;
  width: 1em;
}

/* ---------- LOGIN ---------- */
.login {
  width: 400px;
}

.login__header {
  background-color: #52b3f9;
  border-top-left-radius: 1.25em;
  border-top-right-radius: 1.25em;
  color: #fff;
  padding: 1.25em 1.625em;
}

.login__header :first-child {
  margin-top: 0;
}

.login__header :last-child {
  margin-bottom: 0;
}

.login h2 .icon {
  margin-right: 14px;
}

.login__form {
  background-color: #fff;
  border-bottom-left-radius: 1.25em;
  border-bottom-right-radius: 1.25em;
  color: #777;
  display: grid;
  grid-gap: 0.875em;
  gap: 0.875em;
  padding: 1.25em 1.625em;
}

.login input {
  border-radius: 0.1875em;
}

.login input[type="email"],
.login input[type="password"] {
  background-color: #eee;
  color: #777;
  padding: 0.25em 0.625em;
  width: 100%;
}

.login input[type="button"] {
  display: block;
  margin: 0 auto;
}
</style>
