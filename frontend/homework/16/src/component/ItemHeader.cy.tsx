import React from 'react'
import { ItemHeader } from './ItemHeader'

describe('<ItemHeader />', () => {
  it('renders', () => {
    // see: https://on.cypress.io/mounting-react
    cy.mount(<ItemHeader />)
  })
})