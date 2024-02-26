import { useRef } from "react";

export const ScrollToTop = () => {
  const scrollRef = useRef<HTMLButtonElement>(null);

  const scrollToTop = () => {
    if (scrollRef.current) {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    }
  };

  return (
    <button onClick={scrollToTop} style={{width:'100%',height:'5rem'}} ref={scrollRef}>
      Scroll To Top
    </button>
  );
};
