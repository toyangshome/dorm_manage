<template>
  <a-card>
    <a-form
      ref='formRef'
      :rules='rules'
      :model='formState'
      label-align='right'
      :label-col='{ span: 8,offset:2 }'
      :wrapper-col='{ span: 4 }'
      @finish='finish'
    >
      <a-form-item has-feedback required label='旧密码' name='oldPassword'>
        <a-input-password v-model:value='formState.oldPassword' />
      </a-form-item>
      <a-form-item has-feedback required label='新密码' name='newPassword'>
        <a-input-password v-model:value='formState.newPassword' />
      </a-form-item>
      <a-form-item has-feedback required label='再次输入' name='repeatPassword'>
        <a-input-password v-model:value='formState.repeatPassword' />
      </a-form-item>
      <a-form-item :wrapper-col='{ span: 2,offset:11 }'>
        <a-button type='primary' html-type='submit'>提交</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script lang='ts' setup>

import { reactive, ref } from 'vue'
import { FormInstance, message } from 'ant-design-vue'
import PasswordAPI from '@/api/password'
import { RuleObject } from 'ant-design-vue/es/form'
import useUserStore from '@/store/userStore'
import { RoleEnum } from '@/@types'

interface FormState {
  oldPassword: string
  newPassword: string
  repeatPassword: string
}

const formRef = ref<FormInstance>()
const formState = reactive({
  oldPassword: '',
  newPassword: '',
  repeatPassword: ''
})
let validatePass = async (_rule: RuleObject, value: string) => {
  if (value === '') {
    return Promise.reject('Please input the password')
  } else {
    if (formState.repeatPassword !== '') {
      formRef.value.validateFields('checkPass')
    }
    return Promise.resolve()
  }
}
let validatePass2 = async (_rule: RuleObject, value: string) => {
  if (value === '') {
    return Promise.reject('Please input the password again')
  } else if (value !== formState.newPassword) {
    return Promise.reject('Two inputs don\'t match!')
  } else {
    return Promise.resolve()
  }
}

const rules = {
  oldPassword: [{ required: true, validator: validatePass, trigger: 'change' }],
  newPassword: [{ required: true, validator: validatePass, trigger: 'change' }],
  repeatPassword: [{ validator: validatePass2, trigger: 'change' }]
}

const userStore = useUserStore()
const curRole = userStore.$state.currentRole
let id
if (curRole === RoleEnum.ADMIN) {
  id = userStore.$state.userInfo.adminId
} else if (curRole === RoleEnum.STUDENT) {
  id = userStore.$state.userInfo.studentId
} else {
  id = userStore.$state.userInfo.dormManId
}
const finish = (values: FormState) => {
  PasswordAPI.change({
    role: curRole,
    id,
    oldPassword: values.oldPassword,
    newPassword: values.newPassword
  }).then(({ data: res }) => {
    formRef.value.resetFields()
    if (res.code === 200) {
      return message.success('修改密码成功')
    }
    return message.error(res.message)
  })
}
</script>

<style lang='scss' scoped>

</style>
