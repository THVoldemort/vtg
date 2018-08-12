import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HotelMySuffix from './hotel-my-suffix';
import HotelMySuffixDetail from './hotel-my-suffix-detail';
import HotelMySuffixUpdate from './hotel-my-suffix-update';
import HotelMySuffixDeleteDialog from './hotel-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HotelMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HotelMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HotelMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={HotelMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HotelMySuffixDeleteDialog} />
  </>
);

export default Routes;
