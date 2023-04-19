import path from 'path'
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'

export function svgPlugin(forBuild: boolean) {
    return createSvgIconsPlugin({
        svgoOptions: forBuild,
        iconDirs: [path.resolve(process.cwd(), 'src/assets/icons')]
    })
}