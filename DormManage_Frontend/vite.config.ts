import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'
import vueJsx from '@vitejs/plugin-vue-jsx'
import importerPlugin from 'vite-plugin-importer'
import viteCompression from 'vite-plugin-compression'
// https://vitejs.dev/config/
export default defineConfig({
  build: {
    chunkSizeWarningLimit: 500,
    minify: 'terser',
    terserOptions: {
      compress: {
        drop_console: true,
        drop_debugger: true,
        pure_funcs: ['console.log']
      },
      output: {
        comments: true
      }
    }
  },
  plugins: [
    vue(),
    vueJsx(),
    importerPlugin({
      libraryName: 'ant-design-vue',
      libraryDirectory: 'es',
      style: true // less
    }),
    viteCompression()
  ],
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src')
    },
    extensions: ['.js', '.json', '.ts', '.tsx']
  },
  css: {
    preprocessorOptions: {
      less: {
        javascriptEnabled: true
      },
      sass: {
        javascriptEnabled: true
      }
    }
  }
})
