import { PluginOption } from "vite";
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import eslintPlugin from 'vite-plugin-eslint'
import { svgPlugin } from "./svg";

export function createVitePlugin(forBuild:boolean) {
    const vitePlugins: PluginOption[] = [vue(), vueJsx()]
    vitePlugins.push(svgPlugin(forBuild))
    vitePlugins.push(eslintPlugin({include: ['src/**/*.js','src/**/*.vue','src/**/*.vuex','src/**/*.ts','src/**/*.tsx']}))
    return vitePlugins
}