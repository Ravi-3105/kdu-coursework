import { BrowserRouter, Route, Routes } from "react-router-dom"
import Dashboard from "./component/Dashboard"
import Portfolio from "./component/Portfolio"
import StockMarketComponent from "./component/StockMarketComponent"
import React from "react"

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="*" element={<Dashboard />} />
        <Route path="/portfolio" element={<Portfolio/>} />
        <Route path="/stock" element={<StockMarketComponent/>} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
