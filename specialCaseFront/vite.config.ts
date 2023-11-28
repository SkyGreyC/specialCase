import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
import postcssPluginPx2rem from "postcss-plugin-px2rem"

// import VueSetupExtend from 'vite-plugin-vue-setup-extend'

// 配置@别名
import { resolve } from "path";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    // VueSetupExtend(),
    createSvgIconsPlugin({
      // 配置路劲在你的src里的svg存放文件
      iconDirs: [path.resolve(process.cwd(), 'src/assets/icons')],
      symbolId: 'icon-[dir]-[name]',
    }),
  ],
  resolve: {
    // ↓路径别名
    alias: {
      "@": resolve(__dirname, "./src")
    }
  },
  css: {
    postcss: {
      plugins: [
        postcssPluginPx2rem({
          rootValue: 192, //换算基数， 默认100  ，这样的话把根标签的字体规定为1rem为50px,这样就可以从设计稿上量出多少个px直接在代码中写多上px了。
          // unitPrecision: 5, //允许REM单位增长到的十进制数字。
          //propWhiteList: [],  //默认值是一个空数组，这意味着禁用白名单并启用所有属性。
          // propBlackList: [], //黑名单
          // exclude: /(node_module)/, //默认false，可以（reg）利用正则表达式排除某些文件夹的方法，例如/(node_module)/ 。如果想把前端UI框架内的px也转换成rem，请把此属性设为默认值
          // selectorBlackList: [], //要忽略并保留为px的选择器
          // ignoreIdentifier: false,  //（boolean/string）忽略单个属性的方法，启用ignoreidentifier后，replace将自动设置为true。
          // replace: true, // （布尔值）替换包含REM的规则，而不是添加回退。
          mediaQuery: false, //（布尔值）允许在媒体查询中转换px。
          minPixelValue: 3 //设置要替换的最小像素值(3px会被转rem)。 默认 0
        })
      ]
    }
  },
  // server: {
  //   proxy: {
  //     '/api': { // 匹配请求路径，
  //       target: '你要代理的地址', // 代理的目标地址
  //       // 开发模式，默认的127.0.0.1,开启后代理服务会把origin修改为目标地址
  //       changeOrigin: true,
  //       // secure: true, // 是否https接口
  //       // ws: true, // 是否代理websockets

  //       // 路径重写，**** 如果你的后端有统一前缀(如:/api)，就不开启；没有就开启
  //       //简单来说，就是是否改路径 加某些东西
  //       rewrite: (path) => path.replace(/^\/api/, '')
  //     }
  //   }
  // }
})

