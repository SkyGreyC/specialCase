<script setup lang="ts">
import { ref, computed } from 'vue'
import { useMain } from '../store/home'
import { useRoute, useRouter } from 'vue-router'
import { storeToRefs } from 'pinia';

//首页前端栏
const router = useRouter()
const route = useRoute()
const toHome = () => router.push('/')
/**
 * 判断是否为白底背景
 */
const isWhiteBg = computed(() => {
  for (const m of route.matched){
    if (m.meta && m.meta.whiteBg){
      return true
    }
  }
  return false
})

const main = useMain()
// 解构main里面的state和getters的数据，
// 使用storeToRefs解构才有响应式，响应式可以直接修改数据，不过这我只用来渲染
const { counter,name ,doubleCount } = storeToRefs(main)
 
//（常用方法三种）
//常用方法一： 使用数据
console.log( counter );
//使用方法(方法目前不能解构)
main.increment()
 
 
// 常用方法二：修改数据
// counter = 9999
 
//常用方法三：
//进阶使用$patch，多个修改
const amend=()=>{
    main.$patch((state) => {
        state.counter += 10;
        state.name = '张三'
    })
}

defineProps<{ msg: string }>()

const count = ref(0)

//使用方式一：直接使用(和vue2在cretae上用一样，setup自带async，await在顶层可以直接使用)
// import { indexAPI} from "../../request/api";
//     //直接使用，一般用在进入页面入请求数据的接口
//     let res = await indexAPI()
//     console.log( "***" ,res);

//使用方式二：使用 async / await，
//（setup虽然自带async，但单独用await只能在顶层使用，如果在函数下还是要async / await一起写）
// import { returnApplyListAPi } from "../../request/api";
 
// const search = async(val: IUseTableParam) => {
//     let res = await returnApplyListAPi({
//         ...val,
//     })
//     console.log( "***" ,res);
//     let { list, pageNum, pageSize, total } = res.data
//     console.log(list, pageNum, pageSize, total);
// }

//使用.then
// import { returnApplyListAPi} from "../../request/api";
 
// const logout = () => {
//     returnApplyListAPi({
//         ...val,
//     }).then((res) => {
//          console.log('***',res );
//         let { list, pageNum, pageSize, total } = res.data
//     })
// };
</script>

<template>
  <h1>{{ msg }}</h1>
  <div>counter:{{counter}}</div>
    <div>doubleCount:{{doubleCount}}</div>
    <a-button @click="main.randomizeCounter()">counter(round)</a-button>
    <a-button type="primary" @click="main.increment()">counter++</a-button>
 
    <div>{{name}}</div>
    <a-button @click="amend()">修改</a-button>
  <div class="card">
    <button type="button" @click="count++">count is {{ count }}</button>
    <p>
      Edit
      <code>components/HelloWorld.vue</code> to test HMR
    </p>
  </div>

  <p>
    Check out
    <a href="https://vuejs.org/guide/quick-start.html#local" target="_blank"
      >create-vue</a
    >, the official Vue + Vite starter
  </p>
  <p>
    Install
    <a href="https://github.com/vuejs/language-tools" target="_blank">Volar</a>
    in your IDE for a better DX
  </p>
  <p class="read-the-docs">Click on the Vite and Vue logos to learn more</p>
</template>

<style lang="scss" scoped>
.personal-center {
  height: 100vh;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  background-image: url('@/assets/images/index/bg.png');

  &.white-bg {
    background: #fff;
  }

  :deep(.el-backtop){
    background-color: transparent;
    box-shadow: none;
    width: 36px;
    height: 36px;
  }
}
.app-logo{
  cursor: pointer;
  display: flex;
  padding: 3px 20px;
  box-sizing: border-box;
}
.read-the-docs {
  color: #888;
}
</style>
