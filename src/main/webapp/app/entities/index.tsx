import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HotelMySuffix from './hotel-my-suffix';
import ProvinceMySuffix from './province-my-suffix';
import PlaceMySuffix from './place-my-suffix';
import HotelRoomMySuffix from './hotel-room-my-suffix';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/hotel-my-suffix`} component={HotelMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/province-my-suffix`} component={ProvinceMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/place-my-suffix`} component={PlaceMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/hotel-room-my-suffix`} component={HotelRoomMySuffix} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
