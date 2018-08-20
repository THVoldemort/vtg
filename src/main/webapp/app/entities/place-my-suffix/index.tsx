import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PlaceMySuffix from './place-my-suffix';
import PlaceMySuffixDetail from './place-my-suffix-detail';
import PlaceMySuffixUpdate from './place-my-suffix-update';
import PlaceMySuffixDeleteDialog from './place-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PlaceMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PlaceMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PlaceMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={PlaceMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PlaceMySuffixDeleteDialog} />
  </>
);

export default Routes;
