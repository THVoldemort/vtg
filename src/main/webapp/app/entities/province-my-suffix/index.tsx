import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProvinceMySuffix from './province-my-suffix';
import ProvinceMySuffixDetail from './province-my-suffix-detail';
import ProvinceMySuffixUpdate from './province-my-suffix-update';
import ProvinceMySuffixDeleteDialog from './province-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProvinceMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProvinceMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProvinceMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProvinceMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ProvinceMySuffixDeleteDialog} />
  </>
);

export default Routes;
