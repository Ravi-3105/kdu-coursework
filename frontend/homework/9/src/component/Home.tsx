import { FullName } from "./FullName";
import { HobbyList, IHobbyItem } from "./HobbyList";
import { Name } from "./Name";
import './Home.css'
import { Qualification } from "./Qualification";
import { ISkillItem, SkillList } from "./SkillList";

export interface IHome {
    "name": string;
    "fullName": string;
    "qualification": string;
    "skills": ISkillItem[];
    "hobbies": IHobbyItem[];
}
export interface IHomeProps {
    "homeList": IHome;
}

export function Home({ homeList }: IHomeProps) {
    return (
        <div className="profile">
            <Name name={homeList.name} />
            <FullName fullName={homeList.fullName} />
            <Qualification qualification={homeList.qualification} />
            <div className="skills">
                <SkillList skillItems={homeList.skills} />
                <HobbyList hobbyItems={homeList.hobbies} />
            </div>
        </div>
    )
}