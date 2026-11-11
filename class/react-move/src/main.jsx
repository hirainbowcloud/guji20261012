// src/main.jsx
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx' // 👈 確保引入的是 App

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>
)