import ProjectConfig from '@/config'

export const tokenName = ProjectConfig.token.tokenName

function getTokenFromLocal (): string | null {
  return window.localStorage.getItem(tokenName)
}

function setTokenToLocal (token: string): void {
  window.localStorage.setItem(tokenName, token)
}

function clearTokenOfLocal (): void {
  window.localStorage.removeItem(tokenName)
}

function isExist (): boolean {
  return window.localStorage.getItem(tokenName) !== null
}

const TokenUtils = {
  getTokenFromLocal,
  setTokenToLocal,
  clearTokenOfLocal,
  isExist
}

export default TokenUtils
