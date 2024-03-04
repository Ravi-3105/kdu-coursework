import React from 'react'
import { useSelector } from 'react-redux'
import { RootState } from '../redux/store'

export default function Cost() {
  const cost = useSelector((state: RootState) => state.hotel.cost);
  const sty: React.CSSProperties = {
    display: "none"
  }
  const gg: React.CSSProperties = {
    fontWeight: "bold"
  }
  return (
    <div>
      <p style={cost === 0 ? sty : gg}>Cost + 18%GST = {cost}</p>
    </div>
  )
}
