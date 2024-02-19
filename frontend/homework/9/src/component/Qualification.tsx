import './Qualification.css'

export interface IQualificationProps{
    qualification:string;
}
export function Qualification({qualification}:IQualificationProps) {
  return (
    <div className='qualification'>{qualification}</div>
  )
}
