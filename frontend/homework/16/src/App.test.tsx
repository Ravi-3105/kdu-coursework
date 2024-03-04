import { render, screen, fireEvent } from '@testing-library/react';
import { ItemHeader } from './component/ItemHeader';
import { AddHeader } from './component/AddHeader';
import { Header } from './component/Header';
import { Provider } from 'react-redux'
import { store } from './redux/store';
import { ItemInput } from './component/ItemInput';
import App from './App';



// Your test
it('renders Add Item Header message', () => {
    render(<ItemHeader />);
    const message = screen.queryByText(/Items/i);
    expect(message).toBeVisible();
});

it('renders Add Item message', () => {
    render(<AddHeader />);
    const message = screen.queryByText(/Add Item/i);
    expect(message).toBeVisible();
});

it('renders Submit message', () => {
    render(<Provider store={store}>
        <ItemInput />
    </Provider>
    )
    const message = screen.queryByText(/Submit/i);
    expect(message).toBeVisible();
});


it('renders Item Lister message', () => {
    render(<Provider store={store}>
        <Header />
    </Provider>,
    );
    const message = screen.queryByText(/Item Lister/i);
    expect(message).toBeVisible();
});


it('renders Submit message', () => {
    render(
        <Provider store={store}>
            <App/>
        </Provider>
    );

    const input = screen.getByTestId('add-item');
    fireEvent.change(input, { target: { value: 'Test' } });

    const submitButton = screen.getByText('Submit');
    fireEvent.click(submitButton);
    expect(input).toBeInTheDocument();
});

