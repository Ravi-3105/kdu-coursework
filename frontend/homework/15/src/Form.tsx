import { useRef, useEffect } from "react";

export const Form = () => {
    const inputRef = useRef<HTMLInputElement | null>(null);
  
    useEffect(() => {
      if (inputRef.current) {
        inputRef.current.focus();
      }
    }, []);
  
    return (
      <form style={{marginLeft:'30%'}}>
        <input type="text" ref={inputRef} />
        <input type="text" />
        <button type="submit">Submit</button>
      </form>
    );
  };
  