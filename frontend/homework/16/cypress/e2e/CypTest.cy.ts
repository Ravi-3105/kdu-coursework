describe('template spec', () => {
  it('passes', () => {
    cy.visit('http://localhost:5173/')
    cy.get('[data-testid="add-item"]').should("exist");
    cy.get('[data-testid="list"]').should('exist');

    cy.get('ul#list li:first-of-type button').click();
    cy.get('[data-testid="Item 1"]').should('not.exist');


    cy.get('ul#list li:first-of-type button').click();
    cy.get('[data-testid="Item 2"]').should('not.exist');
  })
})