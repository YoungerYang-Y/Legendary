<script lang="ts">
import { UserOutlined, LockOutlined } from "@ant-design/icons-vue";
import type { ValidateErrorEntity } from "ant-design-vue/es/form/interface";
import { reactive, type UnwrapRef } from "vue";
export interface FormState {
  user: string;
  password: string;
}
export default {
  setup() {
    const formState: UnwrapRef<FormState> = reactive({
      user: "",
      password: "",
    });
    const handleFinish = (values: FormState) => {
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
        <a-input v-model:value="formState.user" placeholder="Username">
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
          :disabled="formState.user === '' || formState.password === ''"
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
