$(document).ready(function() {
    $('.mydatatable').DataTable({
        dom: 'Bfrtip',
        scrollY: 500,
        scrollX: true,
        autoWidth: false,
        scrollCollapse: true,
        paging: true
    });
});