
interface ISkillItemProps{
    skill:string;
}

export function SkillItem({skill}: ISkillItemProps){
    return (
        <li className="skillList-item">{skill}</li>
    )
}