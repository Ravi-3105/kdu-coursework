import {SkillItem} from './SkillItem';
import './SkillList.css'

export interface ISkillItem {
    id: number;
    skill: string;
}

interface ISkillProps {
    skillItems: ISkillItem[];
}

export function SkillList({ skillItems }: ISkillProps){

    return (
        <div className='skill'>
        <div className='skillHead'>Skills</div>
        <ul id="skillList">
        {
            skillItems.map((item) => {
                return (
                    <SkillItem key={item.id} skill={item.skill} />
                )
            })
        }
        </ul>
        </div>
    )
}