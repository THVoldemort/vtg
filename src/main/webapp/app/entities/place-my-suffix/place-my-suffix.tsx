import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './place-my-suffix.reducer';
import { IPlaceMySuffix } from 'app/shared/model/place-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlaceMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class PlaceMySuffix extends React.Component<IPlaceMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { placeList, match } = this.props;
    return (
      <div>
        <h2 id="place-my-suffix-heading">
          <Translate contentKey="vtgApp.place.home.title">Places</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vtgApp.place.home.createLabel">Create new Place</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.slogan">Slogan</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.address">Address</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.ratingId">Rating Id</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.filePath1">File Path 1</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.filePath2">File Path 2</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.filePath3">File Path 3</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.filePath4">File Path 4</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.filePath5">File Path 5</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.introduction">Introduction</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.priceFrom">Price From</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.priceTo">Price To</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.latitude">Latitude</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.longitude">Longitude</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.place.provinceId">Province Id</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {placeList.map((place, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${place.id}`} color="link" size="sm">
                      {place.id}
                    </Button>
                  </td>
                  <td>{place.name}</td>
                  <td>{place.slogan}</td>
                  <td>{place.address}</td>
                  <td>{place.ratingId}</td>
                  <td>{place.filePath1}</td>
                  <td>{place.filePath2}</td>
                  <td>{place.filePath3}</td>
                  <td>{place.filePath4}</td>
                  <td>{place.filePath5}</td>
                  <td>{place.introduction}</td>
                  <td>{place.priceFrom}</td>
                  <td>{place.priceTo}</td>
                  <td>{place.latitude}</td>
                  <td>{place.longitude}</td>
                  <td>{place.provinceId}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${place.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${place.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${place.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ place }: IRootState) => ({
  placeList: place.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PlaceMySuffix);
