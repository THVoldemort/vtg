import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HotelRoomMySuffix from './hotel-room-my-suffix';
import HotelRoomMySuffixDetail from './hotel-room-my-suffix-detail';
import HotelRoomMySuffixUpdate from './hotel-room-my-suffix-update';
import HotelRoomMySuffixDeleteDialog from './hotel-room-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HotelRoomMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HotelRoomMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HotelRoomMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={HotelRoomMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HotelRoomMySuffixDeleteDialog} />
  </>
);

export default Routes;
