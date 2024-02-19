import './FullName.css'

export interface IFullNameProps{
    fullName:string;
}
export function FullName({fullName}:IFullNameProps) {
  return (
    <div className='full-name'>{fullName}</div>
  )
}
