<script lang="ts">
import { UserOutlined, LockOutlined } from "@ant-design/icons-vue";
import type { ValidateErrorEntity } from "ant-design-vue/es/form/interface";
import { reactive, type UnwrapRef } from "vue";
import { getUserByUserName } from "@/api/login";

export interface FormState {
  username: string;
  password: string;
}
export default {
  setup() {
    const formState: UnwrapRef<FormState> = reactive({
      username: "yy",
      password: "yy",
    });
    const handleFinish = (values: FormState) => {
      // loginApi(formState);
      getUserByUserName({
        size: 30,
        current: 1,
      });
      console.log(values, formState);
    };
    const handleFinishFailed = (errors: ValidateErrorEntity<FormState>) => {
      console.log(errors);
    };
    return {
      formState,
      handleFinish,
      handleFinishFailed,
    };
  },
  components: {
    UserOutlined,
    LockOutlined,
  },
};
</script>

<template lang="">
  <!-- TODO：加一个框框 -->
  <div>
    <a-form
      layout="vertical"
      class="login-form"
      :model="formState"
      @finish="handleFinish"
      @finishFailed="handleFinishFailed"
    >
      <a-form-item>
        <a-input v-model:value="formState.username" placeholder="Username">
          <template #prefix
            ><UserOutlined style="color: rgba(0, 0, 0, 0.25)"
          /></template>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input
          v-model:value="formState.password"
          type="password"
          placeholder="Password"
        >
          <template #prefix
            ><LockOutlined style="color: rgba(0, 0, 0, 0.25)"
          /></template>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-button
          class="login-button"
          type="primary"
          html-type="submit"
          :disabled="formState.username === '' || formState.password === ''"
        >
          登录
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<style lang="less">
.login-form {
  // 保持左右外边距
  margin: 0 25%;
}
.login-button {
  width: 100%;
}
</style>
